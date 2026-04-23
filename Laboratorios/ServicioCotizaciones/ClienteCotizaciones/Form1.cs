using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClienteCotizaciones
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void btnObtener_Click(object sender, EventArgs e)
        {
            try
            {
                // 1. Instanciamos el cliente usando el nombre de la referencia que creaste
                var cliente = new ReferenciaCotizaciones.ServicioCotizacionSoapClient();

                // 2. Obtenemos la fecha de la caja de texto
                string fecha = txtFecha.Text;

                // 3. Llamamos al servicio web
                string respuesta = cliente.obtenerCotizacion(fecha);

                // 4. Mostramos el resultado
                lblResultado.Text = respuesta;
            }
            catch (Exception ex)
            {
                lblResultado.Text = "Error: " + ex.Message;
            }

        }

        private void btnRegistrar_Click(object sender, EventArgs e)
        {
            try
            {
                var cliente = new ReferenciaCotizaciones.ServicioCotizacionSoapClient();

                string fecha = txtFecha.Text;
                // Convertimos el texto ingresado a un número decimal
                decimal monto = decimal.Parse(txtMonto.Text);

                // Llamamos al método de registrar
                string respuesta = cliente.registrarCotizacion(fecha, monto);

                lblResultado.Text = respuesta;
            }
            catch (FormatException)
            {
                lblResultado.Text = "Por favor, ingresa un número válido en la casilla de Monto.";
            }
            catch (Exception ex)
            {
                lblResultado.Text = "Error: " + ex.Message;
            }
        }
    }
}
