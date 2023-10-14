package com.enigma.tokonyadia.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_customer")
public class Customer {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "address",nullable = true)
    private String address;

    @Column(name = "mobile_phone", nullable = true, unique = true)
    private String mobilePhone;

    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;

}
