package server.mapstruct;

import javax.annotation.processing.Generated;
import server.db.classes.DBPayment;
import server.servlets.dtos.PaymentInfo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-29T05:51:18+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240417-1011, environment: Java 17.0.10 (Eclipse Adoptium)"
)
public class DBPaymentPaymentInfoMapperImpl implements DBPaymentPaymentInfoMapper {

    @Override
    public PaymentInfo dbPaymentToPaymentInfo(DBPayment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentInfo paymentInfo = new PaymentInfo();

        paymentInfo.setAmount( payment.getAmount() );
        paymentInfo.setDescription( payment.getDescription() );
        paymentInfo.setPaymentDate( payment.getPaymentDate() );

        return paymentInfo;
    }
}
