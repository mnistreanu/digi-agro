export class SowingExpensesListModel {
  expenseId: number;
  expenseCategoryId: number;
  expenseDate: string;
  icon: string;
  crop: string;
  variety: string;
  cropAndVariety: string;
  unitOfMeasure: string;
  area: number;
  parcels: string;
  normSow1Ha: number;
  normSowTotal: number;
  actualSown1Ha: number;
  actualSownTotal: number;
  unitPrice: number;
  totalAmount: number;

  createdAt: string;
  createdBy: string;
}
