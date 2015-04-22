using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(finah_web.Startup))]
namespace finah_web
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
