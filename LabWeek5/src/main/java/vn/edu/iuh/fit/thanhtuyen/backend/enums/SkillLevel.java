package vn.edu.iuh.fit.thanhtuyen.backend.enums;

import lombok.Getter;

@Getter
public enum SkillLevel {
    MASTER("master"),
    BEGINER("beginer"),
    ADVANCED("advanced"),
    PROFESSIONAL("professional"),
    IMTERMEDIATE("imtermediate");

    private String value;
    SkillLevel(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
