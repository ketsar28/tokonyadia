package com.enigma.tokonyadia.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "m_seller")
public class Seller {

    @Id
    @GenericGenerator(strategy = "uuid2", name="system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "username", unique = true)
    private String username;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;


    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;
}
