package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum EmployeeStatus {
    TERMINATED(-1),
    ACTIVE(1),
    IN_ACTIVE(0);

    private int value;

    private EmployeeStatus(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}