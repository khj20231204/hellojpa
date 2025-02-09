package hellojava;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.h2.engine.User;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            /* 입력 */
            Member member = new Member();
            member.setId(3);
            member.setName("Josep3");

            em.persist(member);

            /* 수정
             * find => java의 collection처럼 객체를 저장해 주는 기능
             * */
            Member findMember =  em.find(Member.class, 1);
            System.out.println(findMember.getName());
            findMember.setName("HelloJPA44");
            //em.persist(findMember);

            /* 삭제
            em.remove(findMember);
            System.out.println(findMember);
            */

            em.createQuery("select m from Member m");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
