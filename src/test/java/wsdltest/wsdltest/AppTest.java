package wsdltest.wsdltest;

import java.util.Random;

import org.datacontract.schemas._2004._07.mundipagg_one_service.ArrayOfCreditCardTransaction;
import org.datacontract.schemas._2004._07.mundipagg_one_service.CreateOrderRequest;
import org.datacontract.schemas._2004._07.mundipagg_one_service.CreateOrderResponse;
import org.datacontract.schemas._2004._07.mundipagg_one_service.CreditCardBrandEnum;
import org.datacontract.schemas._2004._07.mundipagg_one_service.CreditCardTransaction;
import org.datacontract.schemas._2004._07.mundipagg_one_service.CurrencyIsoEnum;
import org.datacontract.schemas._2004._07.mundipagg_one_service.EmailUpdateToBuyerEnum;
import org.junit.Assert;
import org.junit.Test;
import org.tempuri.MundiPaggService_Service;

public class AppTest {

	@Test
	public void CreateValidOrderTest() {
		

		CreateOrderRequest request = new CreateOrderRequest();

		request.setAmountInCents(5l);
		// Cria um numero aleatorio
		Random generator = new Random();
		Integer randomNumber = generator.nextInt(1000);
		request.setCurrencyIsoEnum(CurrencyIsoEnum.BRL);
		request.setEmailUpdateToBuyerEnum(EmailUpdateToBuyerEnum.NO);
		request.setMerchantKey("8A2DD57F-1ED9-4153-B4CE-69683EFADAD5");

		// Criando uma cole��o de transa��es
		CreditCardTransaction[] creditCardTransactionCollection = new CreditCardTransaction[1];
		CreditCardTransaction creditCardTransaction = new CreditCardTransaction();
		creditCardTransaction.setAmountInCents(request.getAmountInCents());
		creditCardTransaction.setCreditCardBrandEnum(CreditCardBrandEnum.VISA);
		creditCardTransaction.setCreditCardNumber("41111111111111");
		creditCardTransaction.setSecurityCode("123");
		creditCardTransaction.setInstallmentCount(1);
		creditCardTransaction.setHolderName("Stanley Martin Lieber");
		creditCardTransaction.setExpMonth(11);
		creditCardTransaction.setExpYear(2019);

		// PaymentMethodCode 1 para Teste
		creditCardTransaction.setPaymentMethodCode(1);
		creditCardTransactionCollection[0] = creditCardTransaction;
		ArrayOfCreditCardTransaction value = new ArrayOfCreditCardTransaction();
		value.getCreditCardTransaction().add(creditCardTransaction);
		request.setCreditCardTransactionCollection(value );;

		request.setOrderReference(randomNumber.toString());
//		CreateOrderResponse response = client.CreateOrder(request);

		
		MundiPaggService_Service ws = new MundiPaggService_Service();
		CreateOrderResponse response = ws.getBasicHttp().createOrder(request);
		
//		Assert.assertTrue(response.getSuccess());
		Assert.assertTrue(response.getErrorReport() == null);
		
	}
}
