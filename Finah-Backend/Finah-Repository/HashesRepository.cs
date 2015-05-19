using System;
using Finah_DomainClasses;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class HashesRepository
    {
        public IEnumerable<hashes> GetHashes()
        {
            var context = new db_projectEntities();
            var hashesList = context.hashes.ToList();
            return hashesList;
        }

        public hashes GetHashesById(int id)
        {
            var context = new db_projectEntities();
            var hashesWithId = context.hashes.First(h => h.id == id);
            return hashesWithId;
        }

        public hashes AddHashes(hashes newHashes)
        {
            using (var context = new db_projectEntities())
            {
                if (newHashes == null)
                {
                    throw new ArgumentNullException("newHashes");
                }
                context.hashes.Add(newHashes);
                context.SaveChanges();
                return newHashes;
            }
        }

        public Boolean UpdateHashes(int id, hashes hashes)
        {
            if (hashes == null || id == null)
            {
                return false;
            }
            else
            {
                using (var context = new db_projectEntities())
                {
                    var updatedHashes = context.hashes.FirstOrDefault(h => h.id == id);
                    updatedHashes.hash = hashes.hash;
                    updatedHashes.status = hashes.status;
                    updatedHashes.user = hashes.user;
                    updatedHashes.date = hashes.date;
                    context.SaveChanges();
                    return true;
                }
            }
        }

        public void Deletehashes(int id)
        {
            using (var context = new db_projectEntities())
            {
                var hashes = context.hashes.Where(h => h.id == id).ToList();
                foreach (var hash in hashes)
                {
                    context.hashes.Remove(hash);
                }
                context.SaveChanges();
            }
        }
    }
}
