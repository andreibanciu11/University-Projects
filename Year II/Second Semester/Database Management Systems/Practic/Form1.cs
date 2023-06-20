using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Practic
{
    public partial class Form1 : Form
    {
        private string connectionString = "Data Source=DESKTOP-NN3D6V8\\SQLEXPRESS;Initial Catalog=InternshipDB;Integrated Security=True";

        private SqlDataAdapter daParent, daChild;
        private BindingSource bsParent, bsChild;
        private DataSet dataSet;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            string sqlConn = connectionString;
            dataSet = new DataSet();

            daParent = new SqlDataAdapter("SELECT * FROM companies", sqlConn);
            SqlCommandBuilder cb = new SqlCommandBuilder(daParent);
            daParent.Fill(dataSet, "companies");

            daChild = new SqlDataAdapter("SELECT * FROM internships", sqlConn);
            cb = new SqlCommandBuilder(daChild);
            daChild.Fill(dataSet, "internships");

            DataRelation drel = new DataRelation("fk_child_parent",
                dataSet.Tables["companies"].Columns["id"],
                dataSet.Tables["internships"].Columns["companyID"]);
            dataSet.Relations.Add(drel);

            bsParent = new BindingSource();
            bsParent.DataSource = dataSet;
            bsParent.DataMember = "companies";

            bsChild = new BindingSource();
            bsChild.DataSource = bsParent;
            bsChild.DataMember = "fk_child_parent";

            dgvCompanies.DataSource = bsParent;
            dgvInternships.DataSource = bsChild;
        }

        private void removeButton_Click(object sender, EventArgs e)
        {
            if (dgvInternships.SelectedRows.Count > 0)
            {
                DataGridViewRow selectedRow = dgvInternships.SelectedRows[0];
                DataRowView selectedRowView = (DataRowView)selectedRow.DataBoundItem;

                int primaryKeyValue = Convert.ToInt32(selectedRowView.Row["id"]);

                selectedRowView.Delete();
                this.daParent.Update(this.dataSet, "companies");
                this.daChild.Update(this.dataSet, "internships");
            }
        }

        private void saveButton_Click(object sender, EventArgs e)
        {
            this.daParent.Update(this.dataSet, "companies");
            this.daChild.Update(this.dataSet, "internships");
        }
    }
}
