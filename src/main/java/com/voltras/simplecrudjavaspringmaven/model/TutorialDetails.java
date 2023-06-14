package com.voltras.simplecrudjavaspringmaven.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tutorial_details")
public class TutorialDetails {
  @Id
  private Long id;

  @Column
  private Date createdOn;

  @Column
  private String createdBy;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "tutorial_id")
  private Tutorial tutorial;

  public TutorialDetails(String createdBy) {
    this.createdOn = new Date();
    this.createdBy = createdBy;
  }
}