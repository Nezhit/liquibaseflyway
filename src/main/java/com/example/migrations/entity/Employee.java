package com.example.migrations.entity;

import com.example.migrations.dto.EmployeeDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Name", nullable = false, length = 15)
    private String name;

    @Column(name = "Surname", nullable = false, length = 20)
    private String surname;

    @Column(name = "Papaname", length = 20)
    private String papaname;

    @Column(name = "Birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "Address", nullable = false, length = 20)
    private String address;

    @Column(name = "Phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "Passport", nullable = false, unique = true)
    private Integer passport;

    public Employee(EmployeeDto employeeDto){
        this.address=employeeDto.getAddress();
        this.phone=employeeDto.getPhone();
        this.birthdate=employeeDto.getBirthdate();
        this.name= employeeDto.getName();
        this.papaname=employeeDto.getPapaname();
        this.passport=employeeDto.getPassport();
        this.surname=employeeDto.getSurname();
    }

}
