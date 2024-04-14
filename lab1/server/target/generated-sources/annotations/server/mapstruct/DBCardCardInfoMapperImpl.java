package server.mapstruct;

import javax.annotation.processing.Generated;
import server.db.classes.DBCard;
import server.servlets.dtos.CardInfo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-12T16:00:18+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
public class DBCardCardInfoMapperImpl implements DBCardCardInfoMapper {

    @Override
    public CardInfo dbCardToCardInfo(DBCard card) {
        if ( card == null ) {
            return null;
        }

        CardInfo cardInfo = new CardInfo();

        cardInfo.setCvv( card.getCvv() );
        cardInfo.setExpirationDate( card.getExpirationDate() );
        cardInfo.setNumber( card.getNumber() );

        return cardInfo;
    }
}
