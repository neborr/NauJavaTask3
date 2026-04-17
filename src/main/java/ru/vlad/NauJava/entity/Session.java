package ru.vlad.NauJava.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private CinemaHall hall;

    private LocalDateTime startTime;
    private double price;

    private String language;
    private boolean isThreeD;

    @OneToMany(mappedBy = "session")
    private List<Ticket> tickets;

    public Session() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
    public CinemaHall getHall() { return hall; }
    public void setHall(CinemaHall hall) { this.hall = hall; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public boolean isThreeD() { return isThreeD; }
    public void setThreeD(boolean threeD) { isThreeD = threeD; }

    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }
}