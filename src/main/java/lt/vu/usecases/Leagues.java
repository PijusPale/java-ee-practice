package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.League;
import lt.vu.persistence.LeaguesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Leagues {

    @Inject
    private LeaguesDAO leaguesDAO;

    @Getter
    @Setter
    private League leagueToCreate = new League();

    @Getter
    private List<League> allLeagues;
    @Getter
    private List<League> loadedLeagues;
    @Getter @Setter
    private String leagueToSearch = "";

    @PostConstruct
    public void init(){
        loadAllLeagues();
    }

    @Transactional
    public void createLeague(){
        this.leaguesDAO.persist(leagueToCreate);
    }

    private void loadAllLeagues(){
        this.allLeagues = leaguesDAO.loadAll();
    }
    @Transactional
    public void loadLeagueLike(){
        this.loadedLeagues = leaguesDAO.loadLeague(leagueToSearch);
    }
}
