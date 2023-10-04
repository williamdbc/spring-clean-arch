package com.colatina.app.service.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class WalletEntity {

    public WalletEntity(AccountEntity account) {
        this.balance = BigDecimal.ZERO;
        this.account = account;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_wallet")
    @SequenceGenerator(name = "seq_wallet", allocationSize = 1, sequenceName = "seq_wallet")
    @Column(name = "id")
    private Integer id;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

}
