package sample.camel;

public class NumberTest {
    private Integer number;

    public NumberTest() {
    }

    public NumberTest(Integer number) {
        this.number = number;
    }

    public NumberTest deepClone(){
        NumberTest object = new NumberTest();
        object.setNumber(this.number);
        return object;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return this.number.toString();
    }
}
