package cz.muni.fi.pv168.agents.backend;

import java.time.LocalDate;
import java.util.Objects;


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

    public Long getId() {
        return id;
    }

    public LocalDate getBorn() {
        return born;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Agent{" + "id=" + id + ", born=" + born + ", level=" + level + ", name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agent other = (Agent) obj;
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        
//      //  
//        if (!Objects.equals(this.id, other.id)) {
//            return false;
//        }

        if (!Objects.equals(this.born, other.born)) {
            return false;
        }
        return true;
    }
    

}
