package com.example.respositories;

import com.example.models.Ticket;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class TicketRepository {

    // Mocking the DB
    private Map<Long, Ticket> map;

    public TicketRepository() {
        map = new HashMap<>();
    }

    public Ticket save(Ticket ticket) {
        if(ticket.getId()==0) {
            ticket.setId(map.size()+1);
        }
        map.put(ticket.getId(), ticket);
        return ticket;
    }

    public Optional<Ticket> findById(long ticketId) {
        return Optional.ofNullable(map.get(ticketId));
    }
}
