using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Finah_BackendServer.Startup))]
namespace Finah_BackendServer
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
