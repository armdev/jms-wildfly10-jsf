package com.writer.facades;

import com.project.dto.UserDTO;
import com.project.interfaces.UserFacadeRemote;
import com.writer.entities.User;
import com.writer.interfaces.UserFacadeLocale;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 *
 * @author Home
 */
@Stateless(mappedName = "userFacade", name = "userFacade")
@Remote(UserFacadeRemote.class)
public class UserFacade extends AbstractFacade<User> implements UserFacadeRemote, UserFacadeLocale {

    @PersistenceContext(unitName = "writer-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public void save(UserDTO user) {
        System.out.println("Received object " + user.getFirstname());
        try {
            Mapper mapper = new DozerBeanMapper();
            User destObject
                    = mapper.map(user, User.class);
            System.out.println("Saving object ");
            getEntityManager().persist(destObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int count() {
        System.out.println("@@@@@Count in facade  ");
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<User> rt = cq.from(User.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
