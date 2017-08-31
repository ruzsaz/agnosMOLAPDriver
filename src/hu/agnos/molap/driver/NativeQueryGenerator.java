/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.agnos.molap.driver;


import hu.agnos.molap.dimension.Dimension;
import hu.agnos.molap.dimension.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author parisek
 */
public class NativeQueryGenerator {

    private List<Dimension> dimensions;
    private int[][] hierarchyInfo;

    public NativeQueryGenerator() {
    }

    public NativeQueryGenerator(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public NativeQueryGenerator(List<Dimension> dimensions, int[][] hierarchyInfo) {
        this.dimensions = dimensions;
        this.hierarchyInfo = hierarchyInfo;
    }

    
    
    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public void setHierarchyInfo(int[][] hierarchyInfo) {
        this.hierarchyInfo = hierarchyInfo;
    }
    
    

    private List cartesianProduct(List list1, List list2) {
        List result = new ArrayList<>();
        for (Object sb1 : list1) {
            for (Object sb2 : list2) {
                StringBuilder newStringBuilder = new StringBuilder(((StringBuilder) sb1).toString());
                newStringBuilder.append(":").append(((StringBuilder) sb2).toString());
                result.add(newStringBuilder);
            }
        }
        return result;
    }

    public String[] getBaseVectorsFromDrillVector(String[] baseVector, String[] drillVector) {
        String[] result = null;
        int drillVectorLength = drillVector.length;

        //ebben a tömben annyi lista lesz, ahány eleme van a drillVectornak
        List[] childrenList = new List[drillVectorLength];

        for (int i = 0; i < drillVectorLength; i++) {
            List children = new ArrayList();
            String oldPath = baseVector[i];
            StringBuilder tempPath = new StringBuilder();
           
            
            //ha van lefúrás
            if (!drillVector[i].equals("0")) {

                int dimIdx = this.hierarchyInfo[i][0];
                int hierarchyIdx = this.hierarchyInfo[i][1];
                
                Dimension dimension = (Dimension) this.dimensions.get(dimIdx);
                
                if (dimension != null) {
//                    System.out.println("dims nem üress: " + dimension.getUniqueName());
//                    Node member = null;

                    if (oldPath != null) {
                         Node node = dimension.getNode(hierarchyIdx, oldPath);
                        if (node.isLeaf()) {
                            tempPath.append(oldPath);
//                System.out.println("oldPath: " + oldPath);
                            children.add(tempPath);

                        //ha nem levélelemről van szó, akkor lefúrunk a gyerekekre
                        } else {
                            int[] childrenId = node.getChildrenId();

                            if (!oldPath.isEmpty()) {
                                tempPath = tempPath.append(oldPath).append(",");
                            }
                            for (int childId : childrenId) {
                                StringBuilder sb = new StringBuilder(tempPath.toString());
                                sb.append(childId);
//                                System.out.println("gyerekId: "+sb.toString());
                                children.add(sb);
                            }
                        }
                    }
                }

            } //ha nem fúrtunk bele az adott dimenzióba
            else {
                //a régi path-t átmásoljuk
                tempPath.append(oldPath);
//                System.out.println("oldPath: " + oldPath);
                children.add(tempPath);

            }
            childrenList[i] = children;
        }
        List list1 = childrenList[0];
        for (int i = 1; i < childrenList.length; i++) {
            list1 = this.cartesianProduct(list1, childrenList[i]);
        }
        int list1Size = list1.size();
        if (list1Size > 0) {
            result = new String[list1Size];
            for (int i = 0; i < list1Size; i++) {
                result[i] = ((StringBuilder) list1.get(i)).toString();

            }
        }
        return result;
    }

}
