package server.mapstruct;

import org.mapstruct.Mapper;


import server.db.classes.DBCard;
import server.servlets.dtos.CardInfo;

@Mapper
public interface DBCardCardInfoMapper {  
  CardInfo dbCardToCardInfo(DBCard card);
}
