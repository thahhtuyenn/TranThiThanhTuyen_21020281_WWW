package vn.edu.iuh.fit.thanhtuyen.backend.enums;

import lombok.Getter;

@Getter
public enum SkillLevel {
    MASTER(1),
    BEGINER(2),
    ADVANCED(3),
    PROFESSIONAL(4),
    IMTERMEDIATE(5);

    private Integer value;
    SkillLevel(Integer value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
