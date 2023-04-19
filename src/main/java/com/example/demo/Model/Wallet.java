package com.example.demo.Model;
import com.example.demo.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NotNull
    @Positive
    private Double totalMoney;
    @NotNull
    private String icon;
    private String backgroundColor;
    @Positive
    private Double moneyLimit;
    @OneToOne
    private Account account;
}
