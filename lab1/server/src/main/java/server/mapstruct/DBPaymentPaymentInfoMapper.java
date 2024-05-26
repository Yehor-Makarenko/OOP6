package server.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import server.db.classes.DBPayment;
import server.servlets.dtos.PaymentInfo;

@Mapper
public interface DBPaymentPaymentInfoMapper {  
  PaymentInfo dbPaymentToPaymentInfo(DBPayment payment);
}
