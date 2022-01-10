package entity;

import java.util.Objects;

public class Calculation {
    private Integer resultId;
    private String userId;
    private Double firstSide;
    private Double secondSide;
    private Double result;

    public Calculation() {
    }

    public Calculation(Integer resultId, String userId, Double firstSide, Double secondSide, Double result) {
        this.resultId = resultId;
        this.userId = userId;
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.result = result;
    }

    public Calculation(String userId, Double firstSide, Double secondSide, Double result) {
        this.userId = userId;
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.result = result;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getFirstSide() {
        return firstSide;
    }

    public void setFirstSide(Double firstSide) {
        this.firstSide = firstSide;
    }

    public Double getSecondSide() {
        return secondSide;
    }

    public void setSecondSide(Double secondSide) {
        this.secondSide = secondSide;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculation that = (Calculation) o;
        return Objects.equals(resultId, that.resultId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultId);
    }

    @Override
    public String toString() {
        return "Calculation{" +
                "resultId=" + resultId +
                ", userId='" + userId + '\'' +
                ", firstSide=" + firstSide +
                ", secondSide=" + secondSide +
                ", result=" + result +
                '}';
    }
}
