package com.colatina.app.service.dataprovider.entity;

import com.colatina.app.service.core.domain.enumeration.AccountStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_account")
    @SequenceGenerator(name = "seq_account", allocationSize = 1, sequenceName = "seq_account")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "document")
    private String document;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    //@Column(name = "status")
    //private String status;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToOne(mappedBy = "account")
    @JoinColumn(name = "wallet_id")
    private WalletEntity wallet;

}
