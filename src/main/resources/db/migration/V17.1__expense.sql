ALTER TABLE expense ADD COLUMN root_category_id int8;
ALTER TABLE expense ADD COLUMN sub_category_id int8;

ALTER TABLE expense
    ADD CONSTRAINT fk_expense_root_category_id
    FOREIGN KEY (root_category_id)
    REFERENCES expense_category;

ALTER TABLE expense
    ADD CONSTRAINT fk_expense_sub_category_id
    FOREIGN KEY (sub_category_id)
    REFERENCES expense_category;
