package vn.edu.iuh.fit.thanhtuyen.backend.enums;

import lombok.Getter;

@Getter
public enum SkillType {
    SOFT_SKILL("softSkill"),
    UNSPECIFIC("unspecific"),
    TECHNICAL_SKILL("technicalSkill");
    private String value;
    SkillType(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
