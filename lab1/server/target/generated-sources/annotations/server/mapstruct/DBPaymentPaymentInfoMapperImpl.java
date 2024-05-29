package server.mapstruct;

import javax.annotation.processing.Generated;
import server.db.classes.DBPayment;
import server.servlets.dtos.PaymentInfo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-26T19:26:01+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
public class DBPaymentPaymentInfoMapperImpl implements DBPaymentPaymentInfoMapper {

    @Override
    public PaymentInfo dbPaymentToPaymentInfo(DBPayment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentInfo paymentInfo = new PaymentInfo();

        paymentInfo.setAmount( payment.getAmount() );
        paymentInfo.setPaymentDate( payment.getPaymentDate() );
        paymentInfo.setDescription( payment.getDescription() );

        return paymentInfo;
    }
}
