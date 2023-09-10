package org.example;

import org.example.crud.*;
import org.example.utils.*;

public class Main {

    public static void main(String[] args) {
        MigrationUtil flywayMigration = new MigrationUtil();
        flywayMigration.migration();

        ClientCrudService clientCrudService = new ClientCrudService();
        PlanetCrudService planetCrudService = new PlanetCrudService();
        TicketCrudService ticketCrudService = new TicketCrudService();
        ticketCrudService.createTicket(clientCrudService.getClientById(7),
                planetCrudService.getPlanetById("EARTH123"), planetCrudService.getPlanetById("YUP123"));
        ticketCrudService.updateTicketById(9, clientCrudService.getClientById(10),
                planetCrudService.getPlanetById("EARTH1"), planetCrudService.getPlanetById("MARS"));
        System.out.println(ticketCrudService.getTicketById(1));
        ticketCrudService.deleteTicketById(10);
        System.out.println(ticketCrudService.getAll());
    }

}