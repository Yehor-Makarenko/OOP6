package server.mapstruct;

import javax.annotation.processing.Generated;
import server.db.classes.DBCard;
import server.servlets.dtos.CardInfo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-26T19:26:00+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
public class DBCardCardInfoMapperImpl implements DBCardCardInfoMapper {

    @Override
    public CardInfo dbCardToCardInfo(DBCard card) {
        if ( card == null ) {
            return null;
        }

        CardInfo cardInfo = new CardInfo();

        cardInfo.setNumber( card.getNumber() );
        cardInfo.setExpirationDate( card.getExpirationDate() );
        cardInfo.setCvv( card.getCvv() );

        return cardInfo;
    }
}
