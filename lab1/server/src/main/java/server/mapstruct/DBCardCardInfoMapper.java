package server.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import server.db.classes.DBCard;
import server.servlets.dtos.CardInfo;

@Mapper
public interface DBCardCardInfoMapper {   
  CardInfo dbCardToCardInfo(DBCard card);
}
