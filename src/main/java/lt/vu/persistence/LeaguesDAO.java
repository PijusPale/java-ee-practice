package lt.vu.persistence;

import lt.vu.entities.League;
import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class LeaguesDAO {

    @Inject
    private EntityManager em;

    public List<League> loadAll() {
        return em.createNamedQuery("League.findAll", League.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(League league){
        this.em.persist(league);
    }

    public League findOne(Integer id) {
        return em.find(League.class, id);
    }
    public List loadLeague(String s) {
        s = s.toUpperCase();
        return em.createQuery(
                "select l " +
                    "from League as l " +
                    "where upper(l.name) like :leagueName")
                .setParameter("leagueName", s == null ? "" : "%" + s + "%").
                getResultList();
    }
}
