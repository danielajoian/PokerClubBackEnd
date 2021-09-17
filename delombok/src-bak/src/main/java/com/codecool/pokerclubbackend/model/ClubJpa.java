package com.codecool.pokerclubbackend.model;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.Objects;
//@NoArgsConstructor
@Entity
@Table(name = "clubs")
public class ClubJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clubUsername;
    private String password;
    private String city;
    private String country;
    private String address;
    private String email;
    private String site;
    private Integer phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClubJpa clubJpa = (ClubJpa) o;
        return Objects.equals(id, clubJpa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public static class ClubJpaBuilder {
        @SuppressWarnings("all")
        private Long id;
        @SuppressWarnings("all")
        private String clubUsername;
        @SuppressWarnings("all")
        private String password;
        @SuppressWarnings("all")
        private String city;
        @SuppressWarnings("all")
        private String country;
        @SuppressWarnings("all")
        private String address;
        @SuppressWarnings("all")
        private String email;
        @SuppressWarnings("all")
        private String site;
        @SuppressWarnings("all")
        private Integer phoneNumber;

        @SuppressWarnings("all")
        ClubJpaBuilder() {
        }

        @SuppressWarnings("all")
        public ClubJpa.ClubJpaBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        @SuppressWarnings("all")
        public ClubJpa.ClubJpaBuilder clubUsername(final String clubUsername) {
            this.clubUsername = clubUsername;
            return this;
        }

        @SuppressWarnings("all")
        public ClubJpa.ClubJpaBuilder password(final String password) {
            this.password = password;
            return this;
        }

        @SuppressWarnings("all")
        public ClubJpa.ClubJpaBuilder city(final String city) {
            this.city = city;
            return this;
        }

        @SuppressWarnings("all")
        public ClubJpa.ClubJpaBuilder country(final String country) {
            this.country = country;
            return this;
        }

        @SuppressWarnings("all")
        public ClubJpa.ClubJpaBuilder address(final String address) {
            this.address = address;
            return this;
        }

        @SuppressWarnings("all")
        public ClubJpa.ClubJpaBuilder email(final String email) {
            this.email = email;
            return this;
        }

        @SuppressWarnings("all")
        public ClubJpa.ClubJpaBuilder site(final String site) {
            this.site = site;
            return this;
        }

        @SuppressWarnings("all")
        public ClubJpa.ClubJpaBuilder phoneNumber(final Integer phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        @SuppressWarnings("all")
        public ClubJpa build() {
            return new ClubJpa(this.id, this.clubUsername, this.password, this.city, this.country, this.address, this.email, this.site, this.phoneNumber);
        }

        @Override
        @SuppressWarnings("all")
        public String toString() {
            return "ClubJpa.ClubJpaBuilder(id=" + this.id + ", clubUsername=" + this.clubUsername + ", password=" + this.password + ", city=" + this.city + ", country=" + this.country + ", address=" + this.address + ", email=" + this.email + ", site=" + this.site + ", phoneNumber=" + this.phoneNumber + ")";
        }
    }

    @SuppressWarnings("all")
    public static ClubJpa.ClubJpaBuilder builder() {
        return new ClubJpa.ClubJpaBuilder();
    }

    @SuppressWarnings("all")
    public Long getId() {
        return this.id;
    }

    @SuppressWarnings("all")
    public String getClubUsername() {
        return this.clubUsername;
    }

    @SuppressWarnings("all")
    public String getPassword() {
        return this.password;
    }

    @SuppressWarnings("all")
    public String getCity() {
        return this.city;
    }

    @SuppressWarnings("all")
    public String getCountry() {
        return this.country;
    }

    @SuppressWarnings("all")
    public String getAddress() {
        return this.address;
    }

    @SuppressWarnings("all")
    public String getEmail() {
        return this.email;
    }

    @SuppressWarnings("all")
    public String getSite() {
        return this.site;
    }

    @SuppressWarnings("all")
    public Integer getPhoneNumber() {
        return this.phoneNumber;
    }

    @SuppressWarnings("all")
    public void setId(final Long id) {
        this.id = id;
    }

    @SuppressWarnings("all")
    public void setClubUsername(final String clubUsername) {
        this.clubUsername = clubUsername;
    }

    @SuppressWarnings("all")
    public void setPassword(final String password) {
        this.password = password;
    }

    @SuppressWarnings("all")
    public void setCity(final String city) {
        this.city = city;
    }

    @SuppressWarnings("all")
    public void setCountry(final String country) {
        this.country = country;
    }

    @SuppressWarnings("all")
    public void setAddress(final String address) {
        this.address = address;
    }

    @SuppressWarnings("all")
    public void setEmail(final String email) {
        this.email = email;
    }

    @SuppressWarnings("all")
    public void setSite(final String site) {
        this.site = site;
    }

    @SuppressWarnings("all")
    public void setPhoneNumber(final Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    @SuppressWarnings("all")
    public String toString() {
        return "ClubJpa(id=" + this.getId() + ", clubUsername=" + this.getClubUsername() + ", password=" + this.getPassword() + ", city=" + this.getCity() + ", country=" + this.getCountry() + ", address=" + this.getAddress() + ", email=" + this.getEmail() + ", site=" + this.getSite() + ", phoneNumber=" + this.getPhoneNumber() + ")";
    }

    @SuppressWarnings("all")
    public ClubJpa() {
    }

    @SuppressWarnings("all")
    public ClubJpa(final Long id, final String clubUsername, final String password, final String city, final String country, final String address, final String email, final String site, final Integer phoneNumber) {
        this.id = id;
        this.clubUsername = clubUsername;
        this.password = password;
        this.city = city;
        this.country = country;
        this.address = address;
        this.email = email;
        this.site = site;
        this.phoneNumber = phoneNumber;
    }
    //</editor-fold>
}
