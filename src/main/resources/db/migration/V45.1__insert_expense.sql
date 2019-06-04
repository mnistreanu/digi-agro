UPDATE expense AS u SET
root_category_id = CASE WHEN ec.parent_id IS null THEN ec.id ELSE ec.parent_id END,
sub_category_id = CASE WHEN ec.parent_id IS null THEN null ELSE ec.id END

FROM expense AS e
JOIN expense_category AS ec ON ec.id = e.category_id
WHERE u.id = e.id;

