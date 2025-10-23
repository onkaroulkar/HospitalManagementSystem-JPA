package com.onkar.hospitalManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Insurance {

    @Id
    private Long id;

    @Column(nullable = false,unique = true,length = 50)
    private String policyNo;

    @Column(nullable = false,length = 100)
    private String provider;

    @Column(nullable = false)
    private LocalDate validUntil;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "insurance")
    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Insurance(Long id, String policyNo, String provider, LocalDate validUntil, LocalDateTime createdAt) {
        this.id = id;
        this.policyNo = policyNo;
        this.provider = provider;
        this.validUntil = validUntil;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", policyNo='" + policyNo + '\'' +
                ", provider='" + provider + '\'' +
                ", validUntil=" + validUntil +
                ", createdAt=" + createdAt +
                '}';
    }
}
