package cz.muni.fi.pv168.agents.backend;

import java.time.LocalDate;
import java.util.Objects;


public class Mission {

    private Long id;
    private String name;
    private LocalDate lastEdit;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastEdit(LocalDate lastEdit) {
        this.lastEdit = lastEdit;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public LocalDate getLastEdit() {
        return lastEdit;
    }

    @Override
    public String toString() {
        return "Body{"
                + "id=" + id
                + ", name=" + name
                + ", lastEdit=" + lastEdit
                + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mission other = (Mission) obj;
        if (obj != this && this.id == null) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }



}
