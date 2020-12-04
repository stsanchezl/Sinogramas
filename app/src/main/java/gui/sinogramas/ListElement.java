package gui.sinogramas;

public class ListElement implements Comparable<ListElement> {
    public String sinogram;
    public String radical;
    public String meaning;
    public String pronunciation;
    public String strokes;

    public ListElement(String sinogram, String radical, String meaning, String pronunciation, String strokes) {
        this.sinogram = sinogram;
        this.radical = radical;
        this.meaning = meaning;
        this.pronunciation = pronunciation;
        this.strokes = strokes;
    }

    public String getSinogram() {
        return sinogram;
    }

    public void setSinogram(String sinogram) {
        this.sinogram = sinogram;
    }

    public String getRadical() {
        return radical;
    }

    public void setRadical(String radical) {
        this.radical = radical;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getStrokes() {
        return strokes;
    }

    public void setStrokes(String strokes) {
        this.strokes = strokes;
    }

    @Override
    public int compareTo(ListElement o) {
        return 0;
    }
}
