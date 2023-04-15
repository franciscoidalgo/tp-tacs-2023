package com.tacs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "date_options")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DateOption {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private LocalDateTime dateTime;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "options_votes",
      joinColumns = @JoinColumn(name = "option_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private List<User> votedBy;
}
