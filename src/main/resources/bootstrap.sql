INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('4a2e9fd3-1127-4b68-9912-84a5c00f6999', 'requestedQuantity', 'Requested quantity', 'J', false, false, true, false, 'NUMERIC', 'Requested override of calculated quantity. This is quantified in dispensing units.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('5ba8b72d-277a-4da8-b10a-23f0cda23cb4', 'totalReceivedQuantity', 'Total received quantity', 'B', false, false, true, false, 'NUMERIC', 'Total quantity received in the reporting period. This is quantified in dispensing units.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('33b2d2e9-3167-46b0-95d4-1295be9afc22', 'beginningBalance', 'Beginning balance', 'A', false, false, true, false, 'NUMERIC', 'Based on the Stock On Hand from the previous period. This is quantified in dispensing units.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('752cda76-0db5-4b6e-bb79-0f531ab78e2c', 'stockOnHand', 'Stock on hand', 'E', false, false, true, false, 'NUMERIC', 'Current physical count of stock on hand. This is quantified in dispensing units.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('9e825396-269d-4873-baa4-89054e2722f4', 'totalConsumedQuantity', 'Total consumed quantity', 'C', false, false, true, false, 'NUMERIC', 'Quantity dispensed/consumed in the reporting period. This is quantified in dispensing units.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('cd57f329-f549-4717-882e-ecbf98122c38', 'totalLossesAndAdjustments', 'Total losses and adjustments', 'D', false, false, true, false, 'NUMERIC', 'All kind of losses/adjustments made at the facility.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('6b8d331b-a0dd-4a1f-aafb-40e6a72ab9f5', 'requestedQuantityExplanation', 'Requested quantity explanation', 'W', false, false, true, false, 'TEXT', 'Explanation of request for a quantity other than calculated order quantity.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('2ed8c74a-f424-4742-bd14-cfbe67b6e7be', 'remarks', 'Remarks', 'L', false, false, true, false, 'TEXT', 'Any additional remarks.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('bde01507-3837-47b7-ae08-cec92c0c3cd2', 'productCode', 'Product code', 'O', false, false, false, false, 'TEXT', 'Unique identifier for each commodity/product.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('e53e80de-fc63-4ecb-b6b2-ef376b34c926', 'productName', 'Product', 'R', false, true, false, false, 'TEXT', 'Primary name of the product.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('a62a5fed-c0b6-4d49-8a96-c631da0d0113', 'approvedQuantity', 'Approved quantity', 'K', false, false, true, false, 'NUMERIC', 'Final approved quantity. This is quantified in dispensing units.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('750b9359-c097-4612-8328-d21671f88920', 'totalStockoutDays', 'Total stockout days', 'X', false, false, true, false, 'NUMERIC', 'Total number of days facility was out of stock.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('ef524868-9d0a-11e6-80f5-76304dec7eb7', 'total', 'Total', 'Y', false, false, true, false, 'NUMERIC', 'Total of beginning balance and quantity received.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('61e6d059-10ef-40c4-a6e3-fa7b9ad741ec', 'dispensingUnit', 'Unit/unit of issue', 'U', false, false, true, false, 'TEXT', 'Dispensing unit for this product.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('dc9dde56-593d-4929-81be-d1faec7025a8', 'packsToShip', 'Packs to ship', 'V', false, false, true, false, 'NUMERIC', 'Total packs to be shipped based on pack size and applying rounding rules.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('df524868-9d0a-18e6-80f5-76304ded7ab9', 'pricePerPack', 'Price per pack', 'T', false, false, true, false, 'CURRENCY', 'Price per pack. Will be blank if price is not defined.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('5708ebf9-9317-4420-85aa-71b2ae92643d', 'numberOfNewPatientsAdded', 'Number of new patients added', 'F', false, false, true, false, 'NUMERIC', 'New patients data.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('e3a0c1fc-c2d5-11e6-af2d-3417eb83144e', 'totalCost', 'Total cost', 'Q', false, false, true, false, 'CURRENCY', 'Total cost of the product based on quantity requested. Will be blank if price is not defined.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType) VALUES('c6dffdee-3813-40d9-8737-f531d5adf420', 'skipped', 'skipped', 'S', false, false, false, false, 'BOOLEAN');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('720dd95b-b765-4afb-b7f2-7b22261c32f3', 'adjustedConsumption', 'Adjusted consumption', 'N', false, false, true, false, 'NUMERIC', 'Total consumed quantity after adjusting for stockout days. Quantified in dispensing units.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('89113ec3-40e9-4d81-9516-b56adba7f8cd', 'averageConsumption', 'Average consumption', 'P', false, false, true, false, 'NUMERIC', 'Average consumption over a specified number of periods/months. Quantified in dispensing units.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('913e1a4f-f3b0-40c6-a422-2f73608c6f3d', 'maximumStockQuantity', 'Maximum stock quantity', 'H', false, false, true, false, 'NUMERIC', 'Maximum stock calculated based on consumption and max stock amounts. Quantified in dispensing units.');
INSERT INTO requisition.available_requisition_columns (id, name, label, indicator, mandatory, isDisplayRequired, canChangeOrder, canBeChangedByUser, columnType, definition) VALUES('5528576b-b1e7-48d9-bf32-fd0eefefaa9a', 'calculatedOrderQuantity', 'Calculated order quantity', 'I', false, false, true, false, 'NUMERIC', 'Actual quantity needed after deducting stock in hand. This is quantified in dispensing units.');

INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('4a2e9fd3-1127-4b68-9912-84a5c00f6999', 'USER_INPUT'); -- requestedQuantity
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('5ba8b72d-277a-4da8-b10a-23f0cda23cb4', 'USER_INPUT'); -- totalReceivedQuantity
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('33b2d2e9-3167-46b0-95d4-1295be9afc22', 'USER_INPUT'); -- beginningBalance
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('752cda76-0db5-4b6e-bb79-0f531ab78e2c', 'USER_INPUT'); -- stockOnHand
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('752cda76-0db5-4b6e-bb79-0f531ab78e2c', 'CALCULATED'); -- stockOnHand
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('9e825396-269d-4873-baa4-89054e2722f4', 'USER_INPUT'); -- totalConsumedQuantity
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('9e825396-269d-4873-baa4-89054e2722f4', 'CALCULATED'); -- totalConsumedQuantity
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('cd57f329-f549-4717-882e-ecbf98122c38', 'USER_INPUT'); -- totalLossesAndAdjustments
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('6b8d331b-a0dd-4a1f-aafb-40e6a72ab9f5', 'USER_INPUT'); -- requestedQuantityExplanation
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('2ed8c74a-f424-4742-bd14-cfbe67b6e7be', 'USER_INPUT'); -- remarks
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('bde01507-3837-47b7-ae08-cec92c0c3cd2', 'REFERENCE_DATA'); -- productCode
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('e53e80de-fc63-4ecb-b6b2-ef376b34c926', 'REFERENCE_DATA'); -- productName
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('a62a5fed-c0b6-4d49-8a96-c631da0d0113', 'USER_INPUT'); -- approvedQuantity
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('750b9359-c097-4612-8328-d21671f88920', 'USER_INPUT'); -- totalStockoutDays
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('ef524868-9d0a-11e6-80f5-76304dec7eb7', 'CALCULATED'); -- total
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('61e6d059-10ef-40c4-a6e3-fa7b9ad741ec', 'REFERENCE_DATA'); -- dispensingUnit
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('dc9dde56-593d-4929-81be-d1faec7025a8', 'CALCULATED'); -- packsToShip
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('df524868-9d0a-18e6-80f5-76304ded7ab9', 'REFERENCE_DATA'); -- pricePerPack
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('5708ebf9-9317-4420-85aa-71b2ae92643d', 'USER_INPUT'); -- numberOfNewPatientsAdded
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('e3a0c1fc-c2d5-11e6-af2d-3417eb83144e', 'CALCULATED'); -- totalCost
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('c6dffdee-3813-40d9-8737-f531d5adf420', 'USER_INPUT'); -- skipped
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('720dd95b-b765-4afb-b7f2-7b22261c32f3', 'CALCULATED'); -- adjustedConsumption
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('89113ec3-40e9-4d81-9516-b56adba7f8cd', 'CALCULATED'); -- averageConsumption
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('913e1a4f-f3b0-40c6-a422-2f73608c6f3d', 'CALCULATED'); -- maximumStockQuantity
INSERT INTO requisition.available_requisition_column_sources (columnId, value) VALUES ('5528576b-b1e7-48d9-bf32-fd0eefefaa9a', 'CALCULATED'); -- calculatedOrderQuantity

INSERT INTO requisition.available_requisition_column_options (id, columnId, optionName, optionLabel) VALUES ('34b8e763-71a0-41f1-86b4-1829963f0704', '5708ebf9-9317-4420-85aa-71b2ae92643d', 'newPatientCount', 'New patient count'); -- numberOfNewPatientsAdded
INSERT INTO requisition.available_requisition_column_options (id, columnId, optionName, optionLabel) VALUES ('4957ebb4-297c-459e-a291-812e72286eff', '5708ebf9-9317-4420-85aa-71b2ae92643d', 'dispensingUnitsForNewPatients', 'Dispensing units for new patients'); -- numberOfNewPatientsAdded
INSERT INTO requisition.available_requisition_column_options (id, columnId, optionName, optionLabel) VALUES ('ff2b350c-37f2-4801-b21e-27ca12c12b3c', '913e1a4f-f3b0-40c6-a422-2f73608c6f3d', 'default', 'Default'); -- maximumStockQuantity
