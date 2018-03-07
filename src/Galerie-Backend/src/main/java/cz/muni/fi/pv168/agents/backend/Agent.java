package cz.muni.fi.pv168.agents.backend;

import java.time.LocalDate;


public class Agent {

    private final Long id;
    private final LocalDate born;
    private String level;
    private String name;

    public Agent(Long id, LocalDate born, String level, String name) {
        this.id = id;
        this.born = born;
        this.level = level;
        this.name = name;
    }
    

}
