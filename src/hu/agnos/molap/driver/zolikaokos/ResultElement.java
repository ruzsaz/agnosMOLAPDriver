package hu.agnos.molap.driver.zolikaokos;

/**
 * Ez az osztály az eredményhalmaz egy sorát reprzentálja
 *
 * @author ruzsaz
 */
public class ResultElement {

    private final String[] header;
    private final int drillVectorId;

    private final double[] measureValues;

    public ResultElement(String[] header, double[] measureValues, int drillVectorId) {
        this.header = header;
        this.measureValues = measureValues;
        this.drillVectorId = drillVectorId;
    }

    public String[] getHeader() {
        return header;
    }

    public double[] getMeasureValues() {
        return measureValues;
    }

    public int getDrillVectorId() {
        return drillVectorId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\tElement:\n\t");
        sb.append(printHeader()).append("\n\t");
        sb.append(printMeasures());
        return sb.toString();
    }

    public String printHeader() {
        StringBuilder sb = new StringBuilder("\tHeader: ");
        for (String s : header) {
            sb.append(s).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String printMeasures() {
        StringBuilder sb = new StringBuilder("\tMeasures: ");
        for (double s : measureValues) {
            sb.append(s).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public ResultElement deepCopy() {
        String[] tempHeader = new String[header.length];
        for (int i = 0; i < header.length; i++) {
            tempHeader[i] = header[i];
        }

        double[] tempMeasureValues = new double[measureValues.length];
        for (int i = 0; i < measureValues.length; i++) {
            tempMeasureValues[i] = measureValues[i];
        }

        return new ResultElement(tempHeader, tempMeasureValues, drillVectorId);
    }

}
