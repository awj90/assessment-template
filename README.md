## GET /

Renders landing page with table of beer styles and the count of beers belonging to each beer style

- Primarily sorted in descending order of count, followed by natural order of style name
- Clicking on beer count makes a request for the next resource below

## GET /beer/style/<styleId>?styleName=<styleName>

- Renders list of all beers of the clicked style together with a short description of the beer and name of brewery producing the beer, if any
- Sorted in natural order of beer name
- Click on Back to choose another beer style
- Click on brewery name to proceed with placing order

## GET /brewery/<breweryId>

- Renders information (eg. address, phone number, website) on the brewery
- Displays a list of beers produced by the brewery with a form to place an order

## POST /brewery<breweryId>/order

- On success, inserts an order document into MongoDB orders collection and renders an order confirmation view with orderId

## MISC

Set/export environment variables before running
