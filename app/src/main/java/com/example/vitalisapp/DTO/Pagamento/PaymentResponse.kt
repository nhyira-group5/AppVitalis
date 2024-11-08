import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName("accounts_info") val accountsInfo: Any?,
    @SerializedName("acquirer_reconciliation") val acquirerReconciliation: List<Any>,
    @SerializedName("additional_info") val additionalInfo: AdditionalInfo,
    @SerializedName("authorization_code") val authorizationCode: Any?,
    @SerializedName("binary_mode") val binaryMode: Boolean,
    @SerializedName("brand_id") val brandId: Any?,
    @SerializedName("build_version") val buildVersion: String,
    @SerializedName("call_for_authorize_id") val callForAuthorizeId: Any?,
    @SerializedName("callback_url") val callbackUrl: Any?,
    @SerializedName("captured") val captured: Boolean,
    @SerializedName("card") val card: Card,
    @SerializedName("charges_details") val chargesDetails: List<Any>,
    @SerializedName("charges_execution_info") val chargesExecutionInfo: ChargesExecutionInfo,
    @SerializedName("collector_id") val collectorId: Long,
    @SerializedName("corporation_id") val corporationId: Any?,
    @SerializedName("counter_currency") val counterCurrency: Any?,
    @SerializedName("coupon_amount") val couponAmount: Double,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("date_approved") val dateApproved: Any?,
    @SerializedName("date_created") val dateCreated: String,
    @SerializedName("date_last_updated") val dateLastUpdated: String,
    @SerializedName("date_of_expiration") val dateOfExpiration: String,
    @SerializedName("deduction_schema") val deductionSchema: Any?,
    @SerializedName("description") val description: String,
    @SerializedName("differential_pricing_id") val differentialPricingId: Any?,
    @SerializedName("external_reference") val externalReference: Any?,
    @SerializedName("fee_details") val feeDetails: List<Any>,
    @SerializedName("financing_group") val financingGroup: Any?,
    @SerializedName("id") val id: Long,
    @SerializedName("installments") val installments: Int,
    @SerializedName("integrator_id") val integratorId: Any?,
    @SerializedName("issuer_id") val issuerId: Long,
    @SerializedName("live_mode") val liveMode: Boolean,
    @SerializedName("marketplace_owner") val marketplaceOwner: Any?,
    @SerializedName("merchant_account_id") val merchantAccountId: Any?,
    @SerializedName("merchant_number") val merchantNumber: Any?,
    @SerializedName("metadata") val metadata: Map<String, Any>,
    @SerializedName("money_release_date") val moneyReleaseDate: Any?,
    @SerializedName("money_release_schema") val moneyReleaseSchema: Any?,
    @SerializedName("money_release_status") val moneyReleaseStatus: String,
    @SerializedName("notification_url") val notificationUrl: Any?,
    @SerializedName("operation_type") val operationType: String,
    @SerializedName("order") val order: Order,
    @SerializedName("payer") val payer: Payer,
    @SerializedName("payment_method") val paymentMethod: PaymentMethod,
    @SerializedName("payment_method_id") val paymentMethodId: String,
    @SerializedName("payment_type_id") val paymentTypeId: String,
    @SerializedName("platform_id") val platformId: Any?,
    @SerializedName("point_of_interaction") val pointOfInteraction: PointOfInteraction,
    @SerializedName("pos_id") val posId: Any?,
    @SerializedName("processing_mode") val processingMode: String,
    @SerializedName("refunds") val refunds: List<Any>,
    @SerializedName("shipping_amount") val shippingAmount: Double,
    @SerializedName("source") val source: Any?,
    @SerializedName("statement_descriptor") val statementDescriptor: String,
    @SerializedName("status") val status: String,
    @SerializedName("status_detail") val statusDetail: String,
    @SerializedName("store_id") val storeId: Any?,
    @SerializedName("transaction_amount") val transactionAmount: Double,
    @SerializedName("transaction_amount_refunded") val transactionAmountRefunded: Double,
    @SerializedName("transaction_details") val transactionDetails: TransactionDetails,
    @SerializedName("uuid") val uuid: String,
    @SerializedName("verification_code") val verificationCode: Any?,
    @SerializedName("wallet_merchant_id") val walletMerchantId: Any?
)

data class AdditionalInfo(
    @SerializedName("authentication_code") val authenticationCode: Any?,
    @SerializedName("available_balance") val availableBalance: Any?,
    @SerializedName("nsu_processadora") val nsuProcessadora: Any?
)

class Card()

data class ChargesExecutionInfo(
    @SerializedName("internal_execution") val internalExecution: InternalExecution
)

data class InternalExecution(
    @SerializedName("date") val date: String,
    @SerializedName("execution_id") val executionId: String
)

class Order()

data class Payer(
    @SerializedName("email") val email: Any?,
    @SerializedName("entity_type") val entityType: Any?,
    @SerializedName("first_name") val firstName: Any?,
    @SerializedName("id") val id: Long,
    @SerializedName("identification") val identification: Identification,
    @SerializedName("last_name") val lastName: Any?,
    @SerializedName("operator_id") val operatorId: Any?,
    @SerializedName("phone") val phone: Phone,
    @SerializedName("type") val type: Any?
)

data class Identification(
    @SerializedName("number") val number: Any?,
    @SerializedName("type") val type: Any?
)

data class Phone(
    @SerializedName("area_code") val areaCode: Any?,
    @SerializedName("extension") val extension: Any?,
    @SerializedName("number") val number: Any?
)

data class PaymentMethod(
    @SerializedName("id") val id: String,
    @SerializedName("issuer_id") val issuerId: Long,
    @SerializedName("type") val type: String
)

data class PointOfInteraction(
    @SerializedName("application_data") val applicationData: ApplicationData,
    @SerializedName("business_info") val businessInfo: BusinessInfo,
    @SerializedName("location") val location: Location,
    @SerializedName("transaction_data") val transactionData: TransactionData
)

data class ApplicationData(
    @SerializedName("name") val name: Any?,
    @SerializedName("version") val version: Any?
)

data class BusinessInfo(
    @SerializedName("branch") val branch: String,
    @SerializedName("sub_unit") val subUnit: String,
    @SerializedName("unit") val unit: String
)

data class Location(
    @SerializedName("source") val source: Any?,
    @SerializedName("state_id") val stateId: Any?
)

data class TransactionData(
    @SerializedName("bank_info") val bankInfo: BankInfo,
    @SerializedName("bank_transfer_id") val bankTransferId: Any?,
    @SerializedName("e2e_id") val e2eId: Any?,
    @SerializedName("financial_institution") val financialInstitution: Any?,
    @SerializedName("qr_code") val qrCode: String,
    @SerializedName("qr_code_base64") val qrCodeBase64: String,
    @SerializedName("ticket_url") val ticketUrl: String
)

data class BankInfo(
    @SerializedName("collector") val collector: Collector,
    @SerializedName("is_same_bank_account_owner") val isSameBankAccountOwner: Any?,
    @SerializedName("origin_bank_id") val originBankId: Any?,
    @SerializedName("origin_wallet_id") val originWalletId: Any?,
    @SerializedName("payer") val payer: PayerInfo
)

data class Collector(
    @SerializedName("account_holder_name") val accountHolderName: String,
    @SerializedName("account_id") val accountId: Any?,
    @SerializedName("long_name") val longName: Any?,
    @SerializedName("transfer_account_id") val transferAccountId: Any?
)

data class PayerInfo(
    @SerializedName("account_id") val accountId: Any?,
    @SerializedName("branch") val branch: Any?,
    @SerializedName("external_account_id") val externalAccountId: Any?,
    @SerializedName("id") val id: Any?,
    @SerializedName("identification") val identification: Any?,
    @SerializedName("long_name") val longName: Any?
)

data class TransactionDetails(
    @SerializedName("acquirer_reference") val acquirerReference: Any?,
    @SerializedName("external_resource_url") val externalResourceUrl: Any?,
    @SerializedName("financial_institution") val financialInstitution: Any?,
    @SerializedName("installment_amount") val installmentAmount: Double,
    @SerializedName("net_received_amount") val netReceivedAmount: Double,
    @SerializedName("overpaid_amount") val overpaidAmount: Double,
    @SerializedName("payable_deferral_period") val payableDeferralPeriod: Any?,
    @SerializedName("payment_method_reference_id") val paymentMethodReferenceId: Any?,
    @SerializedName("receivable_amount") val receivableAmount: Double
)