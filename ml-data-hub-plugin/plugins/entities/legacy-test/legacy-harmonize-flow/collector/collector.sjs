/*
 * Collect IDs plugin
 *
 * @param options - a map containing options. Options are sent from Java
 *
 * @return - an array of ids or uris
 */
function collect(options) {
  xdmp.log(options);
  // return all URIs for the 'load-acme-tech' collection
  return cts.uris();
}

module.exports = {
  collect: collect
};
