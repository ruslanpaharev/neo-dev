package ru.neoflex.dev.spring.entity_graphs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedSubgraph;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CITY")
@NamedEntityGraphs({
    @NamedEntityGraph(name = "city.departments.employees", 
            attributeNodes = {
                    @NamedAttributeNode(value = "departments", subgraph = "departments.employees"),
            },
            subgraphs = {
                    @NamedSubgraph(name = "departments.employees",
                            attributeNodes = @NamedAttributeNode("employees")),
            }),
})
public class City {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private Set<Department> departments;

    public City() {
    }

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Department> getDepartments() {
        return departments;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) &&
                Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public static CityBuilder builder() {
        return new CityBuilder();
    }

    public static final class CityBuilder {
        private Long id;
        private String name;
        private Set<Department> departments;

        private CityBuilder() {
        }


        public CityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CityBuilder departments(Set<Department> departments) {
            this.departments = departments;
            return this;
        }

        public City build() {
            City city = new City();
            city.setId(id);
            city.setName(name);
            city.setDepartments(departments);
            return city;
        }
    }
}
