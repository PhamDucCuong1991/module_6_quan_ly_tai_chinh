package com.example.demo.Model;

import com.example.demo.account.Account;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    @NotNull
    private String name;
    @Positive
    private Double money;
    @NotNull
    private String type;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Account account;

}
