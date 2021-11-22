Page Title:Leasing – a convenient way of buying your vehicle | SEB

# Object Definitions
==================================================================================================================
txt_pageHeader                      xpath        //h2[text()='Leasing calculator']
radioBtn_operatingLease             xpath          //input[@id='calc08-type01']
radioBtn_capitalLease               id           calc08-type02
radioBtn_financialLease             id           calc08-type03
input_carPrice                      id           calc08-sum
input_deposit                       id           calc08-deposit
select_contractPeriod               id           calc08-period
select_depositType                  id           calc08-deposit-type
input_interestRate                  id           calc08-int
label_monthlyPayment                id           monthly-result
input_residualValue                 id           calc08-salvage-value
button_paymentSchedule              id           value-payment-schedule
button_addToCompare                 xpath        //input[@value='Add to compare' or @value='Lisa võrdlusesse']
count_comparisionTableRow           xpath        //h3[text()='Võrdlus']/following-sibling::table[@class='calc-data']/tbody/tr
lnk_genericFirstXpath               xpath       (//a[contains(text(),'${anyLinkOnPage}')])[1]
==================================================================================================================