package org.example.entities;

import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "ticket")
@NoArgsConstructor
@Getter
@Setter
public class Ticket {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Client.class)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client clientId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Planet.class)
    @JoinColumn(name = "from_planet_id", referencedColumnName = "id", nullable = false)
    private Planet fromPlanetId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Planet.class)
    @JoinColumn(name = "to_planet_id", referencedColumnName = "id", nullable = false)
    private Planet toPlanetId;

    public Ticket(Client clientId, Planet fromPlanetId, Planet toPlanetId) {
        this.clientId = clientId;
        this.fromPlanetId = fromPlanetId;
        this.toPlanetId = toPlanetId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", clientId=" + clientId +
                ", fromPlanetId=" + fromPlanetId +
                ", toPlanetId=" + toPlanetId +
                '}';
    }

}