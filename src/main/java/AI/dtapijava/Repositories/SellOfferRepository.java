package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.SellOffer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellOfferRepository extends JpaRepository<SellOffer, Integer> {

    List<SellOffer> findByIsValid(Boolean valid);

    Optional<SellOffer> findById(Integer id);

    @Query("SELECT s FROM " +
            "SellOffer s INNER JOIN Resource r ON s.resource = r " +
            "INNER JOIN User u ON r.user = u " +
            "WHERE u.id = :user")
    List<SellOffer> getAllSellOffersForUserId(@Param("user") int user);

    @Query("SELECT s FROM SellOffer s INNER JOIN Resource r ON r = s.resource " +
            "INNER JOIN Company c ON r.company = c " +
            "WHERE s.isValid = true AND s.amount > 0 AND c.id = :companyId " +
            "ORDER BY s.price, s.date")
    List<SellOffer> getAllPendingSellOffersForCompanyId(@Param("companyId") int companyid, Pageable pageable);

}
