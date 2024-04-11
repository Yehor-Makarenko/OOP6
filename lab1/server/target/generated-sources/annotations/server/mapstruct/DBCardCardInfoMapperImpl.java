package server.mapstruct;

import java.sql.Date;
import javax.annotation.processing.Generated;
import server.db.classes.DBCard;
import server.servlets.dtos.CardInfo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-11T11:08:21+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
public class DBCardCardInfoMapperImpl implements DBCardCardInfoMapper {

    @Override
    public CardInfo dbCardToCardInfo(DBCard card) {
        if ( card == null ) {
            return null;
        }

        int number = 0;
        Date expirationDate = null;
        int cvv = 0;

        number = card.getNumber();
        expirationDate = card.getExpirationDate();
        cvv = card.getCvv();

        CardInfo cardInfo = new CardInfo( number, expirationDate, cvv );

        return cardInfo;
    }
}
