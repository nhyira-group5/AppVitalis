import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName("accounts_info") var accountsInfo: Any? = null,
    @SerializedName("acquirer_reconciliation") var acquirerReconciliation: List<Any>? = emptyList(),
    @SerializedName("additional_info") var additionalInfo: AdditionalInfo? = null,
    @SerializedName("authorization_code") var authorizationCode: Any? = null,
    @SerializedName("binary_mode") var binaryMode: Boolean? = null,
    @SerializedName("brand_id") var brandId: Any? = null,
    @SerializedName("build_version") var buildVersion: String? = null,
    @SerializedName("call_for_authorize_id") var callForAuthorizeId: Any? = null,
    @SerializedName("callback_url") var callbackUrl: Any? = null,
    @SerializedName("captured") var captured: Boolean? = null,
    @SerializedName("card") var card: Card? = null,
    @SerializedName("charges_details") var chargesDetails: List<Any>? = emptyList(),
    @SerializedName("charges_execution_info") var chargesExecutionInfo: ChargesExecutionInfo? = null,
    @SerializedName("collector_id") var collectorId: Long? = null,
    @SerializedName("corporation_id") var corporationId: Any? = null,
    @SerializedName("counter_currency") var counterCurrency: Any? = null,
    @SerializedName("coupon_amount") var couponAmount: Double? = null,
    @SerializedName("currency_id") var currencyId: String? = null,
    @SerializedName("date_approved") var dateApproved: Any? = null,
    @SerializedName("date_created") var dateCreated: String? = null,
    @SerializedName("date_last_updated") var dateLastUpdated: String? = null,
    @SerializedName("date_of_expiration") var dateOfExpiration: String? = null,
    @SerializedName("deduction_schema") var deductionSchema: Any? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("differential_pricing_id") var differentialPricingId: Any? = null,
    @SerializedName("external_reference") var externalReference: Any? = null,
    @SerializedName("fee_details") var feeDetails: List<Any>? = emptyList(),
    @SerializedName("financing_group") var financingGroup: Any? = null,
    @SerializedName("id") var id: Long? = null,
    @SerializedName("installments") var installments: Int? = null,
    @SerializedName("integrator_id") var integratorId: Any? = null,
    @SerializedName("issuer_id") var issuerId: Long? = null,
    @SerializedName("live_mode") var liveMode: Boolean? = null,
    @SerializedName("marketplace_owner") var marketplaceOwner: Any? = null,
    @SerializedName("merchant_account_id") var merchantAccountId: Any? = null,
    @SerializedName("merchant_number") var merchantNumber: Any? = null,
    @SerializedName("metadata") var metadata: Map<String, Any>? = null,
    @SerializedName("money_release_date") var moneyReleaseDate: Any? = null,
    @SerializedName("money_release_schema") var moneyReleaseSchema: Any? = null,
    @SerializedName("money_release_status") var moneyReleaseStatus: String? = null,
    @SerializedName("notification_url") var notificationUrl: Any? = null,
    @SerializedName("operation_type") var operationType: String? = null,
    @SerializedName("order") var order: Order? = null,
    @SerializedName("payer") var payer: Payer? = null,
    @SerializedName("payment_method") var paymentMethod: PaymentMethod? = null,
    @SerializedName("payment_method_id") var paymentMethodId: String? = null,
    @SerializedName("payment_type_id") var paymentTypeId: String? = null,
    @SerializedName("platform_id") var platformId: Any? = null,
    @SerializedName("point_of_interaction") var pointOfInteraction: PointOfInteraction? = null,
    @SerializedName("pos_id") var posId: Any? = null,
    @SerializedName("processing_mode") var processingMode: String? = null,
    @SerializedName("refunds") var refunds: List<Any>? = emptyList(),
    @SerializedName("shipping_amount") var shippingAmount: Double? = null,
    @SerializedName("source") var source: Any? = null,
    @SerializedName("statement_descriptor") var statementDescriptor: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("status_detail") var statusDetail: String? = null,
    @SerializedName("store_id") var storeId: Any? = null,
    @SerializedName("transaction_amount") var transactionAmount: Double? = null,
    @SerializedName("transaction_amount_refunded") var transactionAmountRefunded: Double? = null,
    @SerializedName("transaction_details") var transactionDetails: TransactionDetails? = null,
    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("verification_code") var verificationCode: Any? = null,
    @SerializedName("wallet_merchant_id") var walletMerchantId: Any? = null
)

data class AdditionalInfo(
    @SerializedName("authentication_code") var authenticationCode: Any? = null,
    @SerializedName("available_balance") var availableBalance: Any? = null,
    @SerializedName("nsu_processadora") var nsuProcessadora: Any? = null
)

class Card()

data class ChargesExecutionInfo(
    @SerializedName("internal_execution") var internalExecution: InternalExecution? = null
)

data class InternalExecution(
    @SerializedName("date") var date: String? = null,
    @SerializedName("execution_id") var executionId: String? = null
)

class Order()

data class Payer(
    @SerializedName("email") var email: Any? = null,
    @SerializedName("entity_type") var entityType: Any? = null,
    @SerializedName("first_name") var firstName: Any? = null,
    @SerializedName("id") var id: Long? = null,
    @SerializedName("identification") var identification: Identification? = null,
    @SerializedName("last_name") var lastName: Any? = null,
    @SerializedName("operator_id") var operatorId: Any? = null,
    @SerializedName("phone") var phone: Phone? = null,
    @SerializedName("type") var type: Any? = null
)

data class Identification(
    @SerializedName("number") var number: Any? = null,
    @SerializedName("type") var type: Any? = null
)

data class Phone(
    @SerializedName("area_code") var areaCode: Any? = null,
    @SerializedName("extension") var extension: Any? = null,
    @SerializedName("number") var number: Any? = null
)

data class PaymentMethod(
    @SerializedName("id") var id: String? = null,
    @SerializedName("issuer_id") var issuerId: Long? = null,
    @SerializedName("type") var type: String? = null
)

data class PointOfInteraction(
    @SerializedName("application_data") var applicationData: ApplicationData? = null,
    @SerializedName("business_info") var businessInfo: BusinessInfo? = null,
    @SerializedName("location") var location: Location? = null,
    @SerializedName("transaction_data") var transactionData: TransactionData? = null
)

data class ApplicationData(
    @SerializedName("name") var name: Any? = null,
    @SerializedName("version") var version: Any? = null
)

data class BusinessInfo(
    @SerializedName("branch") var branch: String? = null,
    @SerializedName("sub_unit") var subUnit: String? = null,
    @SerializedName("unit") var unit: String? = null
)

data class Location(
    @SerializedName("source") var source: Any? = null,
    @SerializedName("state_id") var stateId: Any? = null
)

data class TransactionData(
    @SerializedName("bank_info") var bankInfo: BankInfo? = null,
    @SerializedName("bank_transfer_id") var bankTransferId: Any? = null,
    @SerializedName("e2e_id") var e2eId: Any? = null,
    @SerializedName("financial_institution") var financialInstitution: Any? = null,
    @SerializedName("qr_code") var qrCode: String? = null,
    @SerializedName("qr_code_base64") var qrCodeBase64: String? = null,
    @SerializedName("ticket_url") var ticketUrl: String? = null
)

data class BankInfo(
    @SerializedName("collector") var collector: Collector? = null,
    @SerializedName("is_same_bank_account_owner") var isSameBankAccountOwner: Any? = null,
    @SerializedName("origin_bank_id") var originBankId: Any? = null,
    @SerializedName("origin_wallet_id") var originWalletId: Any? = null,
    @SerializedName("payer") var payer: PayerInfo? = null
)

data class Collector(
    @SerializedName("account_holder_name") var accountHolderName: String? = null,
    @SerializedName("account_id") var accountId: Any? = null,
    @SerializedName("long_name") var longName: Any? = null,
    @SerializedName("transfer_account_id") var transferAccountId: Any? = null
)

data class PayerInfo(
    @SerializedName("account_id") var accountId: Any? = null,
    @SerializedName("branch") var branch: Any? = null,
    @SerializedName("external_account_id") var externalAccountId: Any? = null,
    @SerializedName("id") var id: Any? = null,
    @SerializedName("identification") var identification: Any? = null,
    @SerializedName("long_name") var longName: Any? = null
)

data class TransactionDetails(
    @SerializedName("acquirer_reference") var acquirerReference: Any? = null,
    @SerializedName("external_resource_url") var externalResourceUrl: Any? = null,
    @SerializedName("financial_institution") var financialInstitution: Any? = null,
    @SerializedName("installment_amount") var installmentAmount: Double? = null,
    @SerializedName("net_received_amount") var netReceivedAmount: Double? = null,
    @SerializedName("overpaid_amount") var overpaidAmount: Double? = null,
    @SerializedName("payable_deferral_period") var payableDeferralPeriod: Any? = null,
    @SerializedName("payment_method_reference_id") var paymentMethodReferenceId: Any? = null,
    @SerializedName("receivable_amount") var receivableAmount: Double? = null
)